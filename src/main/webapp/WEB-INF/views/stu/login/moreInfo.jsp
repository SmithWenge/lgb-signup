<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>


<style type="text/css">
  form label {
    margin-top: 5px;
  }
</style>
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
  <div class="panel-heading">
    <ul class="nav nav-pills">
      <li role="presentation" ><a>课程详细信息</a></li>
    </ul>
  </div>

  <div class="panel-body">

    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable">
          <tr style="background-color: #2aabd2;">
            <th>课程名</th>
            <th>所属专业</th>
            <th>所属系别</th>
            <th>操作</th>
          </tr>
          <tr>
            <td>${course.courseName}</td>
            <td>${course.majorName}</td>
            <td>${course.departmentName}</td>

            <td>
              <a href="${contextPath}/stu/signUp/${course.courseId}.action" style="text-decoration: none;">
                <button type="button" class="btn btn-warning">报名</button>
              </a>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>
<%@include file="/WEB-INF/include/footer.jsp"%>