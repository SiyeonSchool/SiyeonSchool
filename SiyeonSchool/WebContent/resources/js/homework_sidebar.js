document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("myModal");
    var addIcon = document.getElementById("addIcon");
    var span = document.getElementsByClassName("close")[0];
    var addButton = document.getElementById("addFolderButton");

    addIcon.onclick = function() {
        modal.style.display = "block";
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    addButton.onclick = function() {
        var folderName = document.getElementById("newFolderName").value.trim();
        if (folderName) {
            // AJAX request to add the folder to the database
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "addFolder.homework", true);  // Use the correct URL
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        if (xhr.responseText.trim() === "success") {
                            // Append the new folder to the assignment list on success
                            var ul = document.getElementById("assignmentList");
                            var li = document.createElement("li");
                            li.className = "sideList";
                            li.innerHTML = '<span class="material-symbols-outlined">folder</span>' +
                                '<span class="subject">' + folderName + '</span>' +
                                '<span class="material-symbols-rounded icon edit">edit</span>' +
                                '<span class="material-symbols-outlined">delete</span>';
                            ul.appendChild(li);
                            document.getElementById("newFolderName").value = ""; // Clear the input
                            modal.style.display = "none"; // Hide the modal
                        } else {
                            alert("폴더 추가에 실패했습니다."); // Alert if the server returns "fail"
                        }
                    } else {
                        alert("서버 요청 중 오류가 발생했습니다."); // Alert if there's a server error
                    }
                }
            };

            xhr.send("folderName=" + encodeURIComponent(folderName));
        } else {
            alert("폴더명을 입력하세요."); // Alert if the input is empty
        }
    }
});