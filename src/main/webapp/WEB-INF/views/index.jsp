<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>


<style type="text/css">
    #stuLoginForm {
        margin-top: 2%;
    }
</style>

<form class="form-horizontal col-sm-offset-3" action="${contextPath}/stu/login.action" method="post" id="stuLoginForm">
    <div class="form-group">
        <label for="inputStuLoginName" class="col-sm-2 control-label">学员卡号</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="inputStuLoginName" name="stuCardNum" placeholder="111111">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-primary">登陆</button>
        </div>
    </div>
</form>

<!-- 重置密码Form
<div class="modal fade" id="resetPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">重置密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="${contextPath}/stu/resetPass.action" method="post">
                    <div class="form-group">
                        <label for="adminLoginName" class="col-sm-3 control-label">管理员登陆名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="adminLoginName" placeholder="stu" name="adminLoginName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="adminEmail" class="col-sm-3 control-label">管理员邮箱</label>
                        <div class="col-sm-9">
                            <input type="email" class="form-control" id="adminEmail" placeholder="stu@example.com" name="adminEmail">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-default">重置密码</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button type="button" class="btn btn-primary">发送邮件</button>--%>
            </div>
        </div>
    </div>
</div>
-->

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>