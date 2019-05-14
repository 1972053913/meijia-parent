package cn.itsource.meijia.service;

import cn.itsource.meijia.domain.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IEmployeeService extends IService<Employee> {
    Employee login(String username, String password);
}
