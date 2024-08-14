<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/login.css">
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

#singIn {
	border: 1px solid #77ddff;
	background-color: rgb(223, 247, 255);
	border-radius: 20px;
	width: 1000px;
	height: 600px;
	z-index: 1;
	position: relative;
	margin: auto;
	top: 159.5px;
}

#back{
	width: 210px;
	height: 47px;
	border-radius: 10px;
	background-color: #46D3FF;
	color: white;
	font-size: 26px;
	font-weight: 700;
	border: none;
	position: absolute;
	margin-top: 520px;
	margin-left: 508px;
	letter-spacing: 3px;

}

#submit {
	width: 210px;
	height: 47px;
	border-radius: 10px;
	background-color: #46D3FF;
	color: white;
	font-size: 26px;
	font-weight: 700;
	border: none;
	position: absolute;
	margin-top: 520px;
	margin-left: 280px;
	letter-spacing: 3px;

}

#submit:hover,#back:hover {
	background-color: #00c3ff;
	cursor: pointer;
}

input,select {
	font-size: 16px;
	font-weight: 500;
	border: none;
	border-radius: 7px;
}

#checkId {
	width: 100px;
	height: 27px;
	border-radius: 7px;
	background-color: #46D3FF;
	font-size: 16px;
	font-weight: 700;
	border: none;
	position: absolute;
	letter-spacing: 3px;
}

#checkId:hover {
	background-color: #00c3ff;
	cursor: pointer;
}

tr{
	height: 45px;
}

tr th{
	text-align: center;
	padding-right: 40px;
	letter-spacing: 2px;
}

tr td {
	width: 691px;
	height: 35px;
	position: relative;
	letter-spacing: 1px;
}

#userId {
	width: 580px;
}

.s {
	width: 570px;
	height: 30px;
	margin-right: 10px;
}

.l {
	width: 685px;
	height: 30px;
}

select {
	width: 685px;
	height: 30px;
}

select:hover {
	cursor: pointer;
}
span{color: red;}
form{position: relative;}

table{
	position: absolute;
	margin-top: 30px;
	margin-left: 70px;
	font-weight: 600;
	font-size: 18px;
}
tr:last-child{
	height: 50px;
	vertical-align: bottom;
}

#end{text-align: center;}

input::placeholder{font-size: 13px; color:#a98affe8;}

select option[value=""][disabled] {
	display: none;
}
select:invalid{
	font-size: 13px;
	color: #a98affe8;
}

</style>
</head>

<body>
	<canvas id="canvas" class="canvas"></canvas>
	<!-- <div id="background"> -->
		<div id="singIn">
			<form action="" method="post">
				<table>
					<tr>
						<th><span>*</span> 아이디</th>
						<td><input type="text" class="s" id="userid" placeholder="영문, 숫자 조합으로 입력해주세요.(6~18자)" required><button id="checkId">중복확인</button></td>
					</tr>
					<tr>
						<th><span>*</span> 비밀번호</th>
						<td><input type="text" class="l" placeholder="영문, 숫자,특수문자(!,@,#,$,%,^,&,* 만 사용) 조합으로 입력해주세요.(6~18자)" required></td>
					</tr>
					<tr>
						<th><span>*</span> 비밀번호 확인</th>
						<td><input type="text" class="l" placeholder="비밀번호를 다시 한번 입력해주세요." required></td>
					</tr>
					<tr>
						<th><span>*</span> 이름</th>
						<td><input type="text" class="l" placeholder="이름을 입력해주세요." required></td>
					</tr>
					<tr>
						<th><span>*</span> 생년월일</th>
						<td><input type="text" class="l" placeholder="6자리 입력해주세요. ex) 00.10.20" required></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" class="l" placeholder="상세주소는 필수 입력 항목이 아닙니다. ex) 서울 강남구"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" class="l" placeholder="이메일 형식에 알맞게 입력해주세요."></td>
					</tr>
					<tr>
						<th><span>*</span> 전화번호</th>
						<td><input type="text" class="l" placeholder="-를 포함해 입력해주세요" required></td>
					</tr>
					<tr>
						<th><span>*</span> 질문</th>
						<td><select name="question" required>
								<option value="" disabled selected>아이디/비밀번호 찾기에 사용됩니다.</option>
								<option value="">2</option>
							</select></td>
					</tr>
					<tr>
						<th><span>*</span> 답변</th>
						<td><input type="text" class="l" placeholder="아이디/비밀번호 찾기에 사용됩니다." required></td>
					</tr>
					<tr>
						<td colspan="2" id="end"><span>*</span> 필수 입력 항목입니다.</td>
					</tr>
				</table>
				<button type="submit" id="submit" onclick="alert('관리자에게 회원가입 승인 요청을 보냈습니다.\n관리자의 승인을 기다려주세요.')">회원가입 요청</button>
				<button id="back" onclick="history.back()">회원가입 취소</button>
			</form>
		</div>
	<!-- </div> -->
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
				let hue = Math.floor(Math.random() * 360);
				this.fill = 'hsl(' + hue + ', 95%, 70%)';
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