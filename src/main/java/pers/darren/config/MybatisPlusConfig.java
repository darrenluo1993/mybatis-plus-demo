package pers.darren.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.baomidou.mybatisplus.annotation.DbType.MYSQL;
import static java.time.LocalDateTime.now;
import static pers.darren.enums.DeleteStatus.NOT_DELETED;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(MYSQL));
        return interceptor;
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("deleted", NOT_DELETED, metaObject);
                this.setFieldValByName("createdBy", 1L, metaObject);
                this.setFieldValByName("createdTime", now(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("modifiedBy", 1L, metaObject);
                this.setFieldValByName("modifiedTime", now(), metaObject);
            }
        };
    }
}
