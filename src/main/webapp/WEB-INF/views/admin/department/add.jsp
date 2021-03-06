<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">系添加</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <c:if test="${not empty addFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${addFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/department/add.action" method="post" id="departmentAddForm">
                    <div class="form-group">
                        <label for="inputDepartmentName" class="col-sm-2 control-label">系名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputDepartmentName" placeholder="艺术系" name="departmentName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDepartmentStartDate" class="col-sm-2 control-label">报名开始时间</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" id="inputDepartmentStartDate" name="departmentStartDate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDepartmentStopDate" class="col-sm-2 control-label">报名结束时间</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" id="inputDepartmentStopDate" name="departmentStopDate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminId" class="col-sm-2 control-label">系主任</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputAdminId" name="adminId">
                                <option value="0">--请选择--</option>
                                <c:forEach items="${departmentAdmins}" var="admin">
                                    <option value="${admin.adminId}">${admin.adminName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">添加系</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#departmentAddForm').validate({
            rules: {
                departmentName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10,
                    remote: {
                        url : "${contextPath}/stu/department/departmentName.action",
                        type : "post",
                        dataType : "json",
                        data : {
                            name : function() {
                                return $("#inputDepartmentName").val();
                            }
                        }
                    }
                },
                departmentStartDate: {
                    required: true
                },
                departmentStopDate: {
                    required: true
                }
            },
            messages: {
                departmentName: {
                    required: "请填写系名.",
                    minlength: "系名的长度为2到10.",
                    maxlength: "系名的长度为2到10.",
                    remote: "请填写新的系名."
                },
                departmentStartDate: {
                    required: "请填写报名开始时间."
                },
                departmentStopDate: {
                    required: "请填写报名截止时间."
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>