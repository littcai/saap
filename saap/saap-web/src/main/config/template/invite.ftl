<div style="background:#fbfbfb;width:96%;margin:0 2%; border: 1px solid #e1e1e1;font-size:11pt;padding:10px">
你好，<b>${email}</b>:
<br/>
${sourceUser.userName}邀请你加入${tenantName}：
<br/>
<#if test="${comment??}">
<div style="border-left: 5px solid #eaeaea;color: #333;margin: 20px 20px 0px 10px;padding: 0 20px;">
${comment}
</div>
</#if>
<br/>
<p>请点击链接激活账号：<a href="${url}/login/join.do?code=${activationCode}">${url}/login/join.do?code=${activationCode}</a></p>
(该链接24小时内有效,24小时后需要重新获取)
<br/>
或将以下链接拷贝到浏览器地址栏中:
<p>${url}/login/join.do?code=${activationCode}</p>
<br/>
感谢您对${systemName}的支持，希望您在${systemName}获得愉悦的使用体验。<br/>
该邮件由系统自动发送，请勿直接回复此邮件。
</div>
