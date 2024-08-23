<%@page import="com.kh.user.model.vo.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/login.css">
<script defer src="resources/js/userIdPwdFind.js"></script>
<style>
.canvas {
	top: 0;
	left: 0;
	pointer-events: none;
	width: 100%;
	height: 100%;
	position: absolute;
	z-index: 0;
}

#find{
    width: 650px;
    height: 700px;
    border: 1px solid #77ddff;
	background-color: rgb(223, 247, 255);
	border-radius: 20px;
    z-index: 1;
	position: relative;
	margin: auto;
    top: 109.5px;
}

table{
	width: 90%;
	height: 90%;
	position:relative;
	margin: auto;
	margin-top: 35px;
}

#header{
	text-align: left;
	font-weight: bold;
	font-size: 22px;
}
th{
	font-weight: bold;
	letter-spacing: 2px;
}

select:invalid, input::placeholder{
	font-size: 13px;
	color: #a98affe8;
}

input{
	width: 395px;
	height: 32px;
	border-radius: 5px;
	border: none;
}

button{
	width: 152px;
	height: 47px;
	border-radius: 5px;
	border: none;
	background-color: #77ddff;
	font-weight: 700;
	font-size: 20px;
	letter-spacing: 2px; 
}

button:hover{
	background-color: #00c3ff;
	cursor: pointer;
}

#back,#btn-update{
	margin: 0 10px;
}

select{
	width: 395px;
	height: 32px;
	border: none;
}

select option[value=""][disabled] {
	display: none;
}

</style>
</head>


<% if (request.getAttribute("message") != null) { %>
    <script>
        alert("<%=request.getAttribute("message")%>");
    </script>
<% } %>


<body>
	<script>
		const contextPath = `<%= contextPath %>`;
		console.log("diadasdsadasd:" + contextPath);
	</script>
	<canvas id="canvas" class="canvas"></canvas>
	<div id="find">
		<table>
			<form id="userForm" action="<%=contextPath%>/userIdPwdFind.user" method="post">
				<tr>
					<th colspan="2" id="header">아이디/비밀번호 찾기</th>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" placeholder="이름" required></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="birthday" placeholder="생년월일 6자리" required></td>
				</tr>
				<tr>
					<th>질문</th>
					<td colspan="2">
						<select name="questionNo" id="questionNo" required>
							<option value="" disabled selected>회원가입시 선택한 질문</option>
							<% ArrayList<Question> questionList = (ArrayList<Question>)request.getAttribute("questionList");
						        if (questionList != null && !questionList.isEmpty()) {
						            for (Question question : questionList) {
						    %>
						        <option value="<%= question.getQuestionNo() %>"><%= question.getQuestionContent() %></option>
						    <%     }
						        } else {
						    %> <option value="">질문이 없습니다</option>
						    <% } %>
						</select>
					</td>
				</tr>
				<tr>
					<th>답변</th>
					<td><input type="text" name="questionAnswer" placeholder="회원가입시 입력한 답변" required></td>
				</tr>
				<tr>
					<th colspan="2"><button id="btn-find" onclick="findUser();">찾기</button></th>
				</tr>
	
				<tr>
					<th>아이디</th>
					<td><input type="text" name="userId" id="userId"
						placeholder="아이디는 변경할 수 없습니다." readonly></td>
				</tr>
				<tr id="passwordFields" style="display:none;">
					<th>변경 비밀번호</th>
					<td><input type="password" name="userPwd" id="userPwd"
						placeholder="변경 비밀번호 입력(영문, 숫자,특수문자(!,@,#,$,%,^,&,* 만 사용) 조합 6~18자)" required></td>
				</tr>
				<tr id="passwordConfirmFields" style="display:none;">
					<th>비밀번호 확인</th>
					<td><input type="password" name="confirmPwd"
						placeholder="비밀번호 확인" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="back" onclick="history.back()">이전</button>
						<button type="submit" id="btn-update">변경</button>
					</td>
				</tr>
			</form>
		</table>

	</div>

	<% if (request.getAttribute("foundUserId") != null) { %>
	    <script>
	        document.getElementById('userId').value = '<%=request.getAttribute("foundUserId")%>';
	        showPasswordFields();
	    </script>
	<% } %>
	<script>
	    let mouse, originx, originy, cvs;
		
		 // Safari doesn't support EventTarget
		 var EventTarget = EventTarget || false;
		
		 // addEventListener shorthand
		 if (EventTarget) {
		   EventTarget.prototype.evt = function (event, callback) {
		     return this.addEventListener(event, callback);
		   }
		 } else {
		   window.evt = function (event, callback) {
		     return this.addEventListener(event, callback);
		   };
		   document.evt = function (event, callback) {
		     return this.addEventListener(event, callback);
		   };
		   Element.prototype.evt = function (event, callback) {
		     return this.addEventListener(event, callback);
		   };
		 }
		
		 // getElementById shorthand
		 function $(elemId) {
		     return document.getElementById(elemId);
		 }
		
		 function init() {
		     cvs = $("canvas");
		
		     resizeCanvas(cvs);
		
		     window.evt('resize', () => resizeCanvas(cvs), false);
		     window.evt("mousemove", function (e) {
		         mouse = getMousePos(cvs, e);
		         originx = mouse.x;
		         originy = mouse.y;
		       
		     });
		     
		     var network = new Field(0, 0, 50);
		     var emit = new Emitter(0, 0, 50);
		     
		     animateCanvas(cvs, function () {
		         network.animate();
		         emit.animate();
		     });
		 }
		
		 // Individual particle
		 class Point {
		     constructor(x, y, canvas, dia) {
		         this.canvas = canvas || cvs;
		         this.x = x || 0;
		         this.y = y || 0;
		         this.vx = 0;
		         this.vy = 0;
		         this.speed = Math.random() * .5 + .2;
		         this.angle = Math.random() * 360;
		         this.diaSet = dia || 2 + Math.random() * 10;
		         this.dia = this.diaSet;
		         this.age = 0;
		         let hue = Math.floor(Math.random()*360);
		         this.fill = 'hsl('+hue+', 95%, 70%)';
		         this.line = Math.random() > .5 ? true : false;
		     }
		
		     emit(life) {
		         let s = this.speed * 2;
		         this.angle += Math.random() * 10 - 5;
		         this.x += s * Math.cos(this.angle * Math.PI / 180);
		         this.y += s * Math.sin(this.angle * Math.PI / 180);
		         this.age += 1 / life;
		         this.boundary();
		     }
		
		     boundary() {
		         if (this.x < 0) {
		             this.x = this.canvas.width;
		         }
		         if (this.x > this.canvas.width) {
		             this.x = 0;
		         }
		         if (this.y < 0) {
		             this.y = this.canvas.height;
		         }
		         if (this.y > this.canvas.height) {
		             this.y = 0;
		         }
		     }
		
		     field(life) {
		         let s = this.speed;
		         this.angle += Math.random() * 10 - 5;
		         this.x += s * Math.cos(this.angle * Math.PI / 180);
		         this.y += s * Math.sin(this.angle * Math.PI / 180);
		         this.age += 1 / life;
		         this.boundary();
		     }
		
		     shrink(life) {
		         this.dia = (1 - this.age) * this.diaSet;
		     }
		
		     draw() {
		         let ctx = this.canvas.getContext('2d'),
		             x = this.x,
		             y = this.y,
		             dia = this.dia,
		             age = this.age;
		
		         ctx.beginPath();
		         ctx.fillStyle = this.fill;
		         ctx.strokeStyle = this.fill;
		         ctx.lineWidth = 2;
		         ctx.arc(x, y, dia, 0, 2 * Math.PI);
		         ctx.closePath();
		         
		         this.line !== true ? ctx.fill() : ctx.stroke();
		     }
		 }
		
		 class ParticleGroup {
		
		     setPosition(x, y) {
		         this.x = x;
		         this.y = y;
		     }
		
		     getPosition(x, y) {
		         return {
		             x: this.x,
		             y: this.y
		         };
		     }
		
		     spawn(x, y, amount, dia) {
		
		         var arr = [];
		         dia = dia || false;
		
		         amount = amount || 1;
		
		         if (amount > 1) {
		             for (let i = 0; i < amount; i++) {
		                 if (dia) {
		                     arr.push(new Point(x, y, cvs, dia));
		                 }
		                 else {
		                     arr.push(new Point(x, y, cvs));
		                 }
		                 
		             }
		         } else {
		             arr = new Point(x, y, cvs, dia);
		         }
		
		         return arr;
		     }
		 }
		
		 // Particle Emitter
		 class Emitter extends ParticleGroup {
		     constructor(x, y, life, mouse, dia) {
		         super();
		
		         if (mouse === undefined) {
		             this.mouse = true;
		         } else {
		            //  this.mouse = mouse;
		         }
		
		         this.particles = [];
		         this.x = x || 0;
		         this.y = y || 0;
		         this.life = life || 20;
		         this.canvas = cvs;
		         this.dia = dia || false;
		     }
		
		     animate() {
		         let particles = this.particles;
		         if (this.mouse) {
		             this.setPosition(originx, originy);
		         }
		
		         let mul = 1;
		
		         for (let i = 0; i < mul; i++) {
		             particles.push(this.spawn(this.x, this.y, 1));
		         }
		         
		         if (particles.length > this.life * mul) {
		             for (let i = 0; i < mul; i++) {
		                 particles.shift();
		             }
		         }
		
		         this.render(this.canvas);
		     }
		     
		     render() {
		         let life = this.life;
		         let ctx = this.canvas.getContext('2d');
		         let particles = this.particles;
		
		         for (let i = 0; i < particles.length; i++) {
		             const p = particles[i];
		             p.draw();
		             p.emit(this.life);
		             p.shrink();
		         }
		     }
		 }
		
		 // Particle Field
		 class Field extends ParticleGroup {
		     constructor(x, y, life) {
		         super();
		         this.particles = [];
		         this.canvas = cvs;
		         this.x = x || 0;
		         this.y = y || 0;
		         this.life = life;
		
		         for (let i = 0; i < this.life; i++) {
		             let x = Math.random() * cvs.width,
		                 y = Math.random() * cvs.height;
		
		             this.particles.push(this.spawn(x, y, 1));
		         }
		     }
		
		     animate() {
		         this.render(canvas);
		     }
		
		     render(canvas) {
		         let ctx = this.canvas.getContext('2d');
		         let particles = this.particles;
		
		         for (let i = 0; i < particles.length; i++) {
		             const p = particles[i];
		             p.draw();
		             p.field(this.life);
		         }
		     }
		 }
		
		 // get the mouse position relative to the canvas
		 function getMousePos(cvs, evt) {
		     const rect = cvs.getBoundingClientRect();
		     return {
		         x: evt.clientX - rect.left,
		         y: evt.clientY - rect.top
		     };
		 }
		
		 // animate the canvas
		 function animateCanvas(canvas, callback) {
		
		     const ctx = canvas.getContext('2d');
		     ctx.clearRect(0, 0, canvas.width, canvas.height);
		     callback();
		
		     requestAnimationFrame(animateCanvas.bind(null, canvas, callback));
		 }
		
		 // Update canvas size to fill window
		 function resizeCanvas(canvas) {
		     canvas.width = window.innerWidth;
		     canvas.height = window.innerHeight;
		     originx = canvas.width / 2;
		     originy = canvas.height / 2;
		 }
		
		 init();

    </script>

</body>
</html>