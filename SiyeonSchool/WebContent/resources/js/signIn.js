$(document).ready(function() {
    init();
});

function validateForm() {
    const userId = document.forms["signIn-form"]["userId"].value;
    const userIdRegex = /^[a-zA-Z0-9]{6,12}$/;
    if (!userIdRegex.test(userId)) {
        alert("아이디는 영문과 숫자 조합으로 6~12자여야 합니다.");
        return false;
    }

    const userPwd = document.forms["signIn-form"]["userPwd"].value;
    const userPwdRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{6,18}$/;
    if (!userPwdRegex.test(userPwd)) {
        alert("비밀번호는 영문, 숫자, 특수문자(!,@,#,$,%,^,&,*) 조합으로 6~18자여야 합니다.");
        return false;
    }

    const userPwdCheck = document.forms["signIn-form"]["userPwdCheck"].value;
    if (userPwd !== userPwdCheck) {
        alert("비밀번호와 일치하지 않습니다.");
        return false;
    }

    const userName = document.forms["signIn-form"]["userName"].value;
    if (userName.length < 1) {
        alert("이름을 입력해주세요.");
        return false;
    }

    const birthday = document.forms["signIn-form"]["birthday"].value;
    const birthdayRegex = /^\d{6}$/;
    if (!birthdayRegex.test(birthday)) {
        alert("생년월일은 6자리 형식으로 입력해주세요.");
        return false;
    }

    const email = document.forms["signIn-form"]["email"].value;
    if (email) {
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailRegex.test(email)) {
            alert("이메일 형식이 올바르지 않습니다.");
            return false;
        }
    }

    const phone = document.forms["signIn-form"]["phone"].value;
    const phoneRegex = /^\d{2,3}-\d{3,4}-\d{4}$/;
    if (!phoneRegex.test(phone)) {
        alert("전화번호는 -를 포함하여 올바른 형식으로 입력해주세요.");
        return false;
    }

    const question = document.forms["signIn-form"]["questionNo"].value;
    if (question === "") {
        alert("질문을 선택해주세요.");
        return false;
    }

    const answer = document.forms["signIn-form"]["questionanswer"].value;
    if (answer.length < 1) {
        alert("질문에 대한 답변을 작성해주세요.");
        return false;
    }

    return true;
}

function idCheck() {
    const $idInput = $("#signIn-form input[name=userId]");

    const userId = $idInput.val();

    $.ajax({
        url: "checkId",
        data: { checkId: userId },
        success: function(result) {
            console.log(result);
            
            if(result === 'NNNNN'){
                alert("이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
                $idInput.focus();
            } else {
                if(confirm("사용 가능한 아이디입니다.")) {
                    $("#signIn-form :submit").removeAttr("disabled");
                }
            }
        },
        error: function() {
            console.log("ajax 오류 발생");
        }
    });
}

let mouse, originx, originy, cvs;

function init() {
    cvs = document.getElementById("canvas");

    resizeCanvas(cvs);

    window.addEventListener('resize', () => resizeCanvas(cvs), false);
    window.addEventListener("mousemove", function (e) {
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
            dia = this.dia;

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
                } else {
                    arr.push(new Point(x, y, cvs));
                }
            }
        } else {
            arr = new Point(x, y, cvs, dia);
        }

        return arr;
    }
}

class Emitter extends ParticleGroup {
    constructor(x, y, life, mouse, dia) {
        super();

        this.mouse = mouse === undefined ? true : mouse;
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
        this.render(cvs);
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

function getMousePos(cvs, evt) {
    const rect = cvs.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
}

function animateCanvas(canvas, callback) {
    const ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    callback();

    requestAnimationFrame(animateCanvas.bind(null, canvas, callback));
}

function resizeCanvas(canvas) {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    originx = canvas.width / 2;
    originy = canvas.height / 2;
}
