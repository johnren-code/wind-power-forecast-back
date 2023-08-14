package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.utls.MailUtils;
import com.example.mybatisplus.model.dto.DeleteDTO;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.UserInfoDTO;
import com.example.mybatisplus.model.dto.UserRegister;
import com.example.mybatisplus.model.vo.UserVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.model.domain.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 前端控制器
 *
 * @author rbl
 * @version v1.0
 * @since 2023-06-25
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate template;

    /**
     * 描述：账号密码登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse login(@RequestBody User user) throws Exception {
        String account = user.getAccount();
        String password = user.getPassword();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", account);
        User queryUser = userService.getOne(userQueryWrapper);
        if (queryUser == null) {
            return JsonResponse.message(false, "该用户不存在");
        } else {
            if (!queryUser.getPassword().equals(password)) {
                return JsonResponse.message(false, "密码错误");
            } else {
                return JsonResponse.success(queryUser);
            }
        }
    }
    /**
     * 描述：超级管理员账号密码登录
     */
    @RequestMapping(value = "/loginBySuper", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse loginBySuper(@RequestBody User user) throws Exception {
        String account = user.getAccount();
        String password = user.getPassword();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", account);
        User queryUser = userService.getOne(userQueryWrapper);
        if (queryUser == null) {
            return JsonResponse.message(false, "该用户不存在");
        } else {
            if (!queryUser.getPassword().equals(password)) {
                return JsonResponse.message(false, "密码错误");
            } else if (queryUser.getRole()!=1){
                return JsonResponse.message(false, "登陆的角色不为超级管理员");
            }else {
                return JsonResponse.success(queryUser);
            }
        }
    }
    /**
     * 描述：邮箱验证码登录
     */
    @RequestMapping(value = "/loginByEmail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse loginByEmail(@RequestBody UserInfoDTO userInfoDTO) throws Exception {
        String email = userInfoDTO.getEmail();
        String writeCode = userInfoDTO.getWriteCode();
        System.out.println(writeCode);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email", email);
        User queryUser = userService.getOne(userQueryWrapper);
        if (queryUser == null) {
            return JsonResponse.message(false, "该邮箱未注册，请先去注册");
        } else {
            ValueOperations<String, String> ops = template.opsForValue();
            String tempCode = ops.get(email);
            if (writeCode.equals(tempCode)) {
                return JsonResponse.success(queryUser);
            } else {
                return JsonResponse.message(false, "邮箱验证码错误");
            }
        }
    }

    /**
     * 描述：注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse Register(@RequestBody UserRegister userRegister) throws Exception {
        String email = userRegister.getEmail();
        String code = userRegister.getCode();
        String password = userRegister.getPassword();
        String username = userRegister.getUsername();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email", email);
        User queryUser = userService.getOne(userQueryWrapper);
        ValueOperations<String, String> ops = template.opsForValue();
        String tempCode = ops.get(email);
        if (queryUser != null) {
            return JsonResponse.message(false, "该邮箱已被注册，请更换邮箱");
        }
        if (!code.equals(tempCode)) {
            return JsonResponse.message(false, "验证码输入错误");
        }
        User user = new User();
        user.setAccount(username);
        user.setPassword(password);
        user.setAvatar("./file/202307/default.jpg");
        user.setEmail(email);
        user.setRole(3);
        userService.save(user);
        return JsonResponse.success(user);
    }

    /**
     * 描述：发送验证码
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse login(@RequestBody UserInfoDTO userInfoDTO) throws Exception {
        String email = userInfoDTO.getEmail();
        if (!MailUtils.isValidEmail(email)) {
            return JsonResponse.message(false, "邮箱格式不正确");
        } else {
            String personal = "风能视界系统管理员";
            String subject = "风能视界系统登录验证码";
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            ValueOperations<String, String> ops = template.opsForValue();
            ops.set(email, String.valueOf(otp), 5, TimeUnit.MINUTES);  // 设置验证码的有效期为5分钟
            String content = "【AeroPowerVision(风能视界)】尊敬的用户，您正在进行邮箱登录/注册操作。验证码为：" + otp + "，请勿转发或泄露。如非本人操作请忽略。5分钟内有效。";
            boolean result = MailUtils.sendMail(email, personal, subject, content);
            return JsonResponse.message(true, "发送验证码成功");
        }
    }

    @RequestMapping(value = "/editInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse editInfo(@RequestBody User user) throws Exception{
        User byId = userService.getById(user.getId());
        byId.setEmail(user.getEmail());
        byId.setAccount(user.getAccount());
        byId.setPassword(user.getPassword());
        byId.setAvatar(user.getAvatar());
        byId.setDescription(user.getDescription());
        userService.saveOrUpdate(byId);
        return JsonResponse.success(byId);
    }
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse queryPageList(PageDTO pageDTO, User user){
        Page<UserVO> page = userService.pageList(pageDTO,user);
        return JsonResponse.success(page);
    }

    @RequestMapping(value = "singleDelete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse singDelete(@RequestBody DeleteDTO deleteDTO){
        boolean result=userService.removeById(deleteDTO.getId());
        return JsonResponse.success(result);
    }

    @RequestMapping(value = "batchDeletes",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse batchDeletes(@RequestBody DeleteDTO deleteDTO){
        boolean result=userService.removeByIds(deleteDTO.getIds());
        return JsonResponse.success(result);
    }

    @RequestMapping(value = "exportUser",method = RequestMethod.POST)
    @ResponseBody
    public void exportUser(HttpServletResponse response, @RequestBody User user){
        userService.exportUser(response,user);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@RequestBody User user){
        boolean b = userService.updateById(user);
        return JsonResponse.success(b);
    }
    /**
     * 描述：根据Id 查询
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id) throws Exception {
        User user = userService.getById(id);
        return JsonResponse.success(user);
    }

    /**
     * 描述：根据Id删除
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
     * 描述：根据Id 更新
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUser(User user) throws Exception {
        userService.updateById(user);
        return JsonResponse.success(null);
    }


    /**
     * 描述:创建User
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(User user) throws Exception {
        userService.save(user);
        return JsonResponse.success(null);
    }
}

