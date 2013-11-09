<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.inc"%>
<%@ include file="/common/taglibs.inc"%>
<html lang="en">
  <head>
	<meta name="decorator" content="main_with_bar" />	
	<style type="text/css">
	.workflowWrapper {
		width: 800px;
		overflow: auto;
		padding: 20px 20px 20px 20px;	
	}
	
	.workflow {
		width: 1600px;		
	}
	
	.workflow ul {
		list-style: none;	
	}
	
	.workflow ul li {
		display: inline-block;
		float:left;
	}
	
	.stepNext {		
		width: 40px;
		height:63px;
		background: url(${contextPath}/images/stepArrow.png) no-repeat 50% 50%; 
		display: block;
	}
	
	.stepNextTop {		
		width: 50px;
		height:63px;
		background: url(${contextPath}/images/stepArrowTop.png) no-repeat 50% 0; 
		display: block;
	}
	
	.stepNextMiddle {		
		width: 50px;
		height:63px;
		background: url(${contextPath}/images/stepArrowMiddle.png) no-repeat 50% 0; 
		display: block;
	}
	
	.stepNextBottom {		
		width: 50px;
		height:63px;
		background: url(${contextPath}/images/stepArrowBottom.png) no-repeat 50% 0; 
		display: block;
	}
	
	.stepNextMiddleOut {		
		width: 50px;
		height:63px;
		background: url(${contextPath}/images/stepArrowMiddleOut.png) no-repeat 50% 0; 
		display: block;
	}
	
	.stepNextBottomOut {		
		width: 50px;
		height:63px;
		background: url(${contextPath}/images/stepArrowBottomOut.png) no-repeat 50% 0; 
		display: block;
	}
	
	.state {
		width: 110px;
		height: 63px;	
		line-height:63px;	
		text-align: center;	
		font-size: 16px;
		font-weight: bold;
		display: block;
		margin: 0;
	}
	
	.stateLabel {
		line-height: 31px;font-size: 16px;font-weight: bold;
	}
		
	.stateOn {
		background: url(${contextPath}/images/stateBg.png) no-repeat;
	}
	
	.stateLongOn {
		width:208px;
		height:63px;
		background: url(${contextPath}/images/stateBgLong.png) no-repeat;
	}
	
	.stateOff {
		background: url(${contextPath}/images/stateBgOff.png) no-repeat;
		color: #fff;
	}
	
	.stateLongOff {
		width:208px;
		background: url(${contextPath}/images/stateBgLongOff.png) no-repeat;
		color: #fff;
	}
	
	.subWorkflow {		
		margin: 0;
		padding: 0;
		border:none;
	}
	
	.subWorkflow tr,td {		
		margin: 0;
		padding: 0;
		border: 0;
	}
	
	</style>		
	</head>
	<body> 
		<div class="workflowWrapper">
			<div class="workflow">
				<ul>
					<li><label class="state stateOn">业务受理</label></li>
					<li class="stepNext"></li>
					<li><span class="state stateOn">检验分发</span></li>				
					<li>	<!-- 分支情况 -->
						<div>
							<table class="subWorkflow">
								<tr>
									<td class="stepNextTop"></td>
									<td ><span class="state stateLongOn"><label class="stateLabel">科室接受<br/>(化学科)</label></span></td>
									<td class="stepNext"></td>
									<td><span class="state stateLongOn"><label class="stateLabel">正在检验<br/>(化学科)</label></span></td>
									<td class="stepNext"></td>
									<td><span class="state stateLongOn"><label class="stateLabel">检验完毕<br/>(化学科)</label></span></td>
									<td class="stepNextTop"></td>
								</tr>							
							</table>
						</div>	
						<div>
							<table class="subWorkflow">
								<tr>
									<td class="stepNextMiddle"></td>
									<td><span class="state stateLongOn"><label class="stateLabel">科室接受<br/>(微生物科)</label></span></td>
									<td class="stepNext"></td>
									<td><span class="state stateLongOff"><label class="stateLabel">正在检验<br/>(微生物科)</label></span></td>
									<td class="stepNext"></td>
									<td><span class="state stateLongOff"><label class="stateLabel">检验完毕<br/>(微生物科)</label></span></td>
									<td class="stepNextMiddleOut"></td>
								</tr>
							</table>
						</div>
						<div>
							<table class="subWorkflow">
								<tr>
									<td class="stepNextBottom"></td>
									<td><span class="state stateLongOn"><label class="stateLabel">科室接受<br/>(微生物科)</label></span></td>
									<td class="stepNext"></td>
									<td><span class="state stateLongOn"><label class="stateLabel">正在检验<br/>(微生物科)</label></span></td>
									<td class="stepNext"></td>
									<td><span class="state stateLongOff"><label class="stateLabel">检验完毕<br/>(微生物科)</label></span></td>
									<td class="stepNextBottomOut"></td>
								</tr>
							</table>
						</div>						
					</li>	
					<li><span class="state stateOff">报告审核</span></li>		
					<li class="stepNext"></li>
					<li><span class="state stateOff">报告签发</span></li>		
					<li class="stepNext"></li>
					<li><span class="state stateOff">确认收费</span></li>			
				</ul>
			</div>
		</div>					
	</body>
</html>
