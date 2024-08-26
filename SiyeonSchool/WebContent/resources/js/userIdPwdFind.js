console.log(contextPath);

function findUser() {
    var form = document.getElementById('userFindForm');
    form.action = `${contextPath}/userIdPwdFind.user`;
    form.submit();
}

function updateUserPassword() {
    var form = document.getElementById('pwdUpdateForm');
    form.action = `${contextPath}/UpdateUserPwd.user`;
    form.submit();
}

function showPasswordFields() {
    document.getElementById('passwordFields').style.display = 'table-row';
    document.getElementById('passwordConfirmFields').style.display = 'table-row';
}
