package cn.itsource.meijia.service.impl;

import cn.itsource.meijia.domain.Employee;
import cn.itsource.meijia.mapper.EmployeeMapper;
import cn.itsource.meijia.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public Employee login(String username, String password) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("password",password);
        return baseMapper.selectOne(queryWrapper);
    }
}
