package com.orjujeng.manager.fegin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.manager.entity.AddBindingInfo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.entity.UpdateBindingInfo;
import com.orjujeng.manager.utils.Result;




@FeignClient("TH-ProfileApi")
public interface ProfileApiFeginService {
	
	@GetMapping("/profile/project/getAllProject")
	public Result getAllProject(@RequestParam(required = false) Boolean expiredDate);
	
	@PostMapping("/profile/project/addProject")
	public Result addProject(@RequestBody ProjectInfo projectInfo); 
	
	@PostMapping("/profile/project/updateProject")
	public Result updateProject(@Valid @RequestBody ProjectInfo projectInfo);
	
	@GetMapping("/profile/binding/findbinding")
	public Result findbinding(@RequestParam(required = false) String id,@RequestParam(required = false)String memberId,@RequestParam(required = false) @Length(min = 8, max = 8, message = "Account Number Must 8 Digs") String accountNum,@RequestParam(required = false) String projectCode);
	
	@PostMapping("/profile/project/disableProject")
	public Result disableProject(@RequestParam(required = false) @Pattern (regexp="/^[0-9]*$/", message = "Must Be Correct Id")Integer id);
	
	@GetMapping("/profile/member/getMember")
	public Result getMemberInfoByAccountNum(@RequestParam(required = false) String accountNum);
	
	@PostMapping("/profile/member/addMember")
	public Result addNewProfile(@RequestBody MemberInfo memberInfo);
	
	@PostMapping("/profile/binding/addbinding")
	public Result addbinding(@RequestBody @Valid AddBindingInfo bindingInfo);
	
	@GetMapping("/profile/member/getMemberById")
	public Result getMemberInfoById(@RequestParam(required = false) Integer id);
	
	@PostMapping("/profile/member/updateMember")
	public Result updateMemberInfoByAccountNum(@Valid @RequestBody MemberInfo memberInfo);
	
	@PostMapping("/profile/binding/updatebinding")
	public Result updatebinding(@RequestBody @Valid UpdateBindingInfo bindingInfo);
	
	@PostMapping("/profile/member/deleteMember")
	public Result deleteMember(@RequestParam(required = true,value = "id") Integer id);
	
	@PostMapping("/profile/binding/deletebinding")
	public Result deletebinding(@RequestParam(required = true,value = "accountNum") String accountNum);
	
	@GetMapping("/profile/member/getMemberByManagerId")
	public Result getMemberByManagerId(@RequestParam(required = true,value = "memberId") Integer memberId);
	
}
