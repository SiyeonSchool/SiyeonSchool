document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById("infoModal");
    const btn = document.getElementById("openModal");
    const span = document.getElementsByClassName("close")[0];

    // 정보변경 버튼 클릭 시 모달 열기
    btn.onclick = function () {
        modal.style.display = "block";
    }

    // 닫기 버튼 클릭 시 모달 닫기
    span.onclick = function () {
        modal.style.display = "none";
    }

    // 모달 외부 클릭 시 모달 닫기
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});

document.getElementById('infoForm').addEventListener('submit', function(event) {
    if (!confirm('정말로 변경하시겠습니까?')) {
        event.preventDefault(); // 폼 제출 방지
    }
});