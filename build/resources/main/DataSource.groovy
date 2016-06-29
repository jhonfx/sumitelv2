dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "admin"
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect
    properties {
       maxActive = 50
       maxIdle = 25
       minIdle = 5
       initialSize = 5
       minEvictableIdleTimeMillis = 1800000
       timeBetweenEvictionRunsMillis = 1800000
       maxWait = 10000
       ValidationQuery = 'select 1'
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
    hbm2ddl.auto=''
}

hibernateProperties {
    hibernate.show_sql = false
}

base.catalogo.suffix="mp\\_c\\_%"
base.esquema = 'sumitelv2'
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/${base.esquema}?useOldAliasMetadataBehavior=true"
        }
    }
    test {
        dataSource {
            dbCreate = "validate"
            url = "jdbc:mysql://192.168.16.54/${base.esquema}?useOldAliasMetadataBehavior=true"
        }
    }
    production {
        dataSource {
            dbCreate = "validate"
            url = "jdbc:mysql://localhost/${base.esquema}?useOldAliasMetadataBehavior=true"
        }
    }
}