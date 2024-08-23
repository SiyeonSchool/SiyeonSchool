console.log(contextPath);

function findUser() {
    var form = document.getElementById('userForm');
    form.action = `${contextPath}/userIdPwdFind.user`;
    form.submit();
}

function updateUserPassword() {
    var form = document.getElementById('userForm');
    form.action = `${contextPath}/UpdateUserPwd.user`;
    form.submit();
}

function showPasswordFields() {
    document.getElementById('passwordFields').style.display = 'table-row';
    document.getElementById('passwordConfirmFields').style.display = 'table-row';
    document.getElementById('btn-update').style.display = 'inline-block';
    document.getElementById('btn-find').style.display = 'none';
}
