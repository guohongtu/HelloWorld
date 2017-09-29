$(function(){
	//console.log("hello World");
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPassword)
	
	$('#regist_button').click(registAction);
	$('#regist_username').blur(checkRegistName);
	$('#regist_password').blur(checkRegistPassword);
	$('#final_password').blur(checkConfirm);
})

function checkName(){
	
	var name=$('#count').val();
	var rule=/^\w{4,10}$/;
	if(!rule.test(name)){
		$('#count').next().html('4~10个字符');
		return false;
	}
	$('#count').next().empty();
	return true;
}
function checkPassword(){
	var password=$('#password').val();
	
	var rule=/^\w{4,10}$/;
	if(!rule.test(password)){
		$('#password').next().html('4~10个字符');
		return false;
	}
	$('#password').next().empty();
	return true;
	
}
function loginAction(){
	var name=$('#count').val();
	var password=$('#password').val();
	
	var n=checkName()+checkPassword();
	console.log("可以");
	if(n!=2){
		return;
	}
	
	//data对象的属性名与服务器控制器的参数名一致login(name,password)
	var data={"name":name,"password":password}
	$.ajax({
		url:'user/login.do',
		data:data,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.state==0){
				var user=result.data;
				//登录成功将userId保存到cookie
				addCookie("userId",user.id);
				//跳转edit.html
				location.href='edit.html';
				
			}else{
				var msg=result.message;
				if(result.state==2){
					$('#count').next().html(msg);
				}else if(result.state==3){
					
					$('#password').next().html(msg);
				}else{
					alert(msg);
				}
				
				
			}
		},
		error:function(e){
			alert("通信失败！");
		}
	})

}
function checkConfirm(){
	var pwd=$('#regist_password').val();
	var confirm=$('#final_password').val();
	if(pwd && pwd==confirm){
		$('#final_password').next().hide();
		return true;
	}
	$('#final_password').next().show().find('span').html('确认密码');
	return false;
}
function checkRegistPassword(){
	var pwd=$('#regist_password').val().trim();
	var rule=/^\w{4,10}$/;
	if(rule.test(pwd)){
		$('#regist_password').next().hide();
		return true;
	}
	$('#regist_password').next().show().find('span').html('4~10个字符');
	return false;
}

function checkRegistName(){
	var name=$('#regist_username').val().trim();
	var rule=/^\w{4,10}$/;
	if(rule.test(name)){
		$('#regist_username').next().hide();
		return true;
	}
	$('#regist_username').next().show().find('span').html('4~10字符');
}

function registAction(){
	var n=checkRegistName()+checkRegistPassword()+checkConfirm();
	if(n!=3){
		return;
	}
	var name=$('#regist_username').val().trim()
	var nick=$('#nickname').val();
	var password=$('#regist_password').val().trim();
	var confirm=$('#final_password').val().trim();
	
	var url='user/regist.do';
	var data={name:name,
			  nick:nick,
	          password:password,
	          confirm:confirm
	};
	$.post(url,data,function(result){
		if(result.state==0){
			$('#back').click();
			var name=result.data.name;
			$('#count').val(name);
			$('#password').focus();
			
			$('#regist_username').val('');
			$('#nickname').val('')
			$('#regist_password').val('');
			$('#final_password').val('');
		}else if(result.state==4){
			$('#regist_username').next().show()
			.find('span').html(result.message);
		}else if(result.state==3){
			$('#regist_password').next().show()
			.find('span').html(result.message);
		}else{
			alert(result.message);
		}
	});
}









