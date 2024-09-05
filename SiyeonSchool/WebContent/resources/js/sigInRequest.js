// Get the modal
$(document).ready(function() {
    // 정보확인 버튼 클릭 이벤트
    $('.infoBtn').on('click', function() {
        // 버튼의 data- 속성에서 정보 추출
        const username = $(this).data('username');
        const userbirth = $(this).data('birth');
        const email = $(this).data('email');
        const phone = $(this).data('phone');
        const address = $(this).data('address');
        const userNo = $(this).data('userno');
        
        // 모달에 정보 삽입
        $('#modalUserNo').val(userNo);
        $('#modalUsername').text(username);
        $('#modalUserBirthday').text(userbirth);
        $('#modalEmail').text(email);
        $('#modalPhone').text(phone);
        $('#modalAddress').text(address);
        
        // 모달 표시
        $('#userInfoModal').modal('show');
    });
    
    // 승인 버튼 클릭 이벤트
    // $('#approveBtn').on('click', function() {
    //     // location.href = userNo;

    //     alert('회원이 승인되었습니다.');
    //     $('#userInfoModal').modal('hide');
    // });
    
    // 거절 버튼 클릭 이벤트
    // $('#rejectBtn').on('click', function() {
    //     // 거절 처리 로직 추가 (예: AJAX 요청으로 서버에 거절 처리)
    //     alert('회원가입 요청이 거절되었습니다.');
    //     $('#userInfoModal').modal('hide');
    // });
});