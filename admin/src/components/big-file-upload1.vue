<template>
  <uploader ref="uploader" :options="options" @file-success="onFileSuccess">
    <!-- 拖拽区域 -->
    <uploader-drop class="uploader-drop-zone">
      <p>拖拽文件或文件夹到此处</p>
      <uploader-btn>选择文件</uploader-btn>
    </uploader-drop>

    <!-- 上传列表 -->
    <uploader-list></uploader-list>
  </uploader>
</template>

<script>
import SparkMD5 from 'spark-md5'
export default {
  data() {
    return {
      options: {
        target: process.env.VUE_APP_SERVER + '/monitor/api/upload/chunk',
        // 新增缓存控制头
        headers: {
          'Cache-Control': 'no-store', // 彻底禁用浏览器和代理缓存[1,2](@ref)
          'Pragma': 'no-cache'        // 兼容旧版HTTP/1.0
        },
        chunkSize: 5 * 1024 * 1024,     // 5MB分片
        simultaneousUploads: 3,         // 并发数
        testChunks: true,               // 开启分片校验
        allowDuplicateUploads: false, // 禁止重复上传相同文件
        query: { fileType: 'video' },
        checkChunkUploadedByResponse: (chunk, message) => {
          const res = JSON.parse(message)
          return res.uploadedChunks?.includes(chunk.offset + 1)
        }
      }
    }
  },
  mounted() {
    // 阻止浏览器默认拖拽行为
    document.addEventListener('dragover', this.preventDefault, false);
    document.addEventListener('drop', this.preventDefault, false);
  },
  methods: {
    preventDefault(e) {
      e.preventDefault();
      e.stopPropagation();
    },
    onFileSuccess(rootFile, file, response, chunk){
      this.mergeFile(file.uniqueIdentifier, file.name, file.chunks.length)
    },
    async mergeFile(identifier, fileName, totalChunks) {
      try {
        const { data } = await this.$ajax.get(
            `${process.env.VUE_APP_SERVER}/monitor/api/upload/merge`,
            {
              params: {
                identifier,
                fileName,
                totalChunks
              },
              timeout: 30000 // 30秒超时
            }
        );
        if (data.success) {
          console.log(data);
          this.$message.success('文件合并成功');
        }
      } catch (error) {
        if (error.code === 'ECONNABORTED') {
          await this.mergeFile(fileHash, fileName, totalChunks) // 超时重试
        }
        this.$message.error('合并失败：' + error.message);
      }
    },
    async handleFileAdded(file) {
      // 计算文件MD5（用于秒传和分片标识）
      const md5 = await this.calculateMd5(file.file)
      file.uniqueIdentifier = md5

      // 添加错误捕获和重试逻辑
      file.on('error', (err) => {
        console.error('分片上传失败:', err)
        this.handleFileAdded(file) // 自定义重试方法
      })

      // 检查文件是否已存在[6](@ref)
      const { data } = await this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/api/upload/check', {
        params: { md5 }
      })
      if (data.exists) {
        this.$message.success('文件秒传成功！')
        file.cancel()
      }

    },
    calculateMd5(file) {
      return new Promise((resolve) => {
        const spark = new SparkMD5.ArrayBuffer()
        const chunkSize = 2 * 1024 * 1024
        const chunks = Math.ceil(file.size / chunkSize)
        let currentChunk = 0

        const loadNext = () => {
          const start = currentChunk * chunkSize
          const end = start + chunkSize >= file.size ? file.size : start + chunkSize
          const reader = new FileReader()
          reader.onload = e => {
            spark.append(e.target.result)
            currentChunk++
            if (currentChunk < chunks) loadNext()
            else resolve(spark.end())
          }
          reader.readAsArrayBuffer(file.slice(start, end))
        }
        loadNext()
      })
    }
  }
}
</script>
<style scoped>
.uploader-drop-zone {
  min-height: 200px;
  border: 2px dashed #ccc;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
  transition: border-color 0.3s;
}

.uploader-drop-zone:hover {
  border-color: #409EFF; /* 悬停高亮色 */
}

.uploader-drop-zone p {
  color: #666;
  margin-bottom: 10px;
}
</style>