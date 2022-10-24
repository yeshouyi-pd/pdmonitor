package com.pd.server.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@Configuration
@MapperScan(basePackages = "com.pd.server.secondary.mapper", sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class MyBatisConfigSecondary {

	// 配置数据源
	@Bean(name = "secondaryDataSource")
	public DataSource SecondaryDataSource(DBConfigSecondary secondaryConfig) throws SQLException {
		MysqlXADataSource secondaryXaDataSource = new MysqlXADataSource();
		secondaryXaDataSource.setURL(secondaryConfig.getUrl());
		secondaryXaDataSource.setPassword(secondaryConfig.getPassword());
		secondaryXaDataSource.setUser(secondaryConfig.getUsername());
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(secondaryXaDataSource);
		xaDataSource.setUniqueResourceName("secondaryDataSource");
		xaDataSource.setMinPoolSize(secondaryConfig.getMinPoolSize());
		xaDataSource.setMaxPoolSize(secondaryConfig.getMaxPoolSize());
		xaDataSource.setMaxLifetime(secondaryConfig.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(secondaryConfig.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(secondaryConfig.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(secondaryConfig.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(secondaryConfig.getMaxIdleTime());
		xaDataSource.setTestQuery(secondaryConfig.getTestQuery());
		return xaDataSource;
	}

	@Bean(name = "secondarySqlSessionFactory")
	public SqlSessionFactory SecondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//添加XML目录
		 ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        try {
	        	bean.setTypeAliasesPackage("com.pd.server.secondary.domain");
				bean.setMapperLocations(resolver.getResources("classpath*:/mapper/secondary/**/*.xml"));
				return bean.getObject();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	}

	@Bean(name = "secondarySqlSessionTemplate")
	public SqlSessionTemplate SecondarySqlSessionTemplate(
			@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	/**
	 * oracleJDBC 对象
	 * 
	 * @param dataSource
	 * @return
	 */
	
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate SecondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
