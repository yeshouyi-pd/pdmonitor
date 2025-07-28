let key = '11111111';//加密key

//DES 加密 key与后端一样的秘钥(8的倍数)   message(值)
function encryptByDES(message) {
    let keyHex = CryptoJS.enc.Utf8.parse(key);
    let encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}
//DES 解密
function decryptByDES(ciphertext) {
    let keyHex = CryptoJS.enc.Utf8.parse(key);
    let decrypted = CryptoJS.DES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
    }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return decrypted.toString(CryptoJS.enc.Utf8);
}
