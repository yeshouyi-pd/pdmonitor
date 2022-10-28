package com.pd.power.datasource;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.pd.power.main.mapper", sqlSessionTemplateRef = "mainSqlSessionTemplate")
public class MyBatisConfigMain {

	// 配置数据源
	@Primary
	@Bean(name = "mainDataSource")
	public DataSource MainDataSource(DBConfigMain mainConfig) throws SQLException {
		MysqlXADataSource mainXaDataSource = new MysqlXADataSource();
		mainXaDataSource.setURL(mainConfig.getUrl());
		mainXaDataSource.setPassword(mainConfig.getPassword());
		mainXaDataSource.setUser(mainConfig.getUsername());
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mainXaDataSource);
		xaDataSource.setUniqueResourceName("mainDataSource");
		xaDataSource.setMinPoolSize(mainConfig.getMinPoolSize());
		xaDataSource.setMaxPoolSize(mainConfig.getMaxPoolSize());
		xaDataSource.setMaxLifetime(mainConfig.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(mainConfig.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(mainConfig.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(mainConfig.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(mainConfig.getMaxIdleTime());
		xaDataSource.setTestQuery(mainConfig.getTestQuery());
		return xaDataSource;
	}

	@Primary
	@Bean(name = "mainSqlSessionFactory")
	public SqlSessionFactory MainSqlSessionFactory(@Qualifier("mainDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//添加XML目录
		 ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        try {
	        	bean.setTypeAliasesPackage("com.pd.power.main.domain");
	            bean.setMapperLocations(resolver.getResources("classpath*:/mapper/main/**/*.xml"));
	            return bean.getObject();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	}

	@Primary
	@Bean(name = "mainSqlSessionTemplate")
	public SqlSessionTemplate MainSqlSessionTemplate(
			@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	/**
	 * JDBC 对象
	 * @param dataSource
	 * @return
	 */
	
    @Bean(name = "mainJdbcTemplate")
    public JdbcTemplate MainJdbcTemplate(
            @Qualifier("mainDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

	
}
