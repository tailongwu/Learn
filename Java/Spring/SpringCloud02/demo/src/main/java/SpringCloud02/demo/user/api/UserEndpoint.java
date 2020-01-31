package SpringCloud02.demo.user.api;

import SpringCloud02.demo.user.dto.UserDto;
import SpringCloud02.demo.user.entity.User;
import SpringCloud02.demo.user.service.Impl.UserServiceImpl;
import SpringCloud02.demo.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户详情数据", notes = "获取用户详情数据", httpMethod = "GET", tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的主键", dataType = "int", paramType = "path")
    })
    public UserDto detail(@PathVariable Long id){
        User user = this.userService.load(id);
        return (null != user) ? new UserDto(user) : null;
    }
}
