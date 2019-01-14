# 数据库版本：mysql  Ver 8.0.11 for Win64 on x86_64
# 访问数据库方式：JdbcTemplate
# 请求处理流程：请求(localhost:8080/login?phoneNumber=xxx&password=xxx) --> 控制器/controller --> 服务/service --> 数据操作类/Dao -->数据库/datasource
# 每个service接口、Dao接口都声明为bean(类上加@Component),service实现类加@Service, Dao实现类加@Repository, 数据源配置类加@Configuration
# 对于每个需要自动注入的bean, 声明时加@Autowired或在使用该bean的类的构造函数上加@Autowired并传入bean
# 返回数据: 语句return new RespEntity(RespCode.SUCCESS, object);
{
  "code": 0,
  "msg": "请求成功",
  "data": {
    "id": 1,
    "name": "test",
     ...
  }
}
