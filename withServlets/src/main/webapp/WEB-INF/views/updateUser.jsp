<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update</title>
    <meta charset='UTF-8'>

    <title>Create advertisment</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <style><%@include file="/css/style.css"%></style>

    <script type="text/javascript" src="js/signup.js"></script>
</head>

<body>
<form id="editForm" class="form_sign_up" method='post' onsubmit="return validate()" action="${pageContext.servletContext.contextPath}/editUser">

        <c:if test="${error != ''}">
            <div style = "background-color: darksalmon">
                <c:out value="${error}" />
            </div>
        </c:if>

    <h2><span class="centred">User update</span></h2>

        <table class="table1"><tbody>
        <tr><td></td>
            <td><div id="loginError"></div></td></tr>
        <tr><td><b>Login:</b></td>
            <td><input type="text" name='login' id="login" value="<c:out value="${user.getLogin()}"/>"><br>
            </td></tr>


        <tr><td></td>
            <td><div id="passError"></div></td></tr>
        <tr><td> <b>Password:</b></td>
                <td><input type="password" name='password' id="password"  value="<c:out value="${user.getPassword()}"/>"><br>
                </td></tr>

        <tr><td></td>
            <td><div id="emailError"></div></td></tr>
        <tr><td><b>Email:</b></td>
                <td><input type="email" name='email' id="email" value="<c:out value="${user.getEmail()}"/>"><br>
                </td></tr>

        <tr><td></td>
            <td><div id="phoneError"></div></td></tr>
        <tr><td><b>Phone:</b><br></td>
                <td><input type="tel" id="phone" name="phone" pattern="[+]{1}[0-9]{7-14}"  value="<c:out value="${user.getPhone()}"/>"><br>
                </td></tr>

            <tr><td><div class="spaceFill">

            </div></td></tr>
        </tbody></table>

        <c:if test="${userIdfromAdm != null}">
            <input type="hidden" name='userIdfromAdm' value="<c:out value="${user.getId()}" />">
        </c:if>



        <input class="button1"  type='submit' value='Update'/>
        <input class="button1"  type='button' value='Main page' onclick="toMain()"/>

    </form>

</body>
</html>

