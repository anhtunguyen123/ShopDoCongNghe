const APIurl = "http://localhost:8080/api/nguoiDung";

const btnRegister = document.getElementById('registerForm');
const name = document.getElementById('name');
const username = document.getElementById('username');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm-password');
const phone = document.getElementById('phone');
const address = document.getElementById('address');
const errorMessageElement = document.getElementById('error-message');

// Modal elements
const modal = document.getElementById('myModal');
const modalMessage = document.getElementById('modal-message');
const closeModal = document.getElementsByClassName('close')[0];
const modalOkButton = document.getElementById('modal-ok-button');

btnRegister.addEventListener('submit', function(event) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của form

    // Kiểm tra mật khẩu và xác nhận mật khẩu
    if (password.value !== confirmPassword.value) {
        errorMessageElement.textContent = "Mật khẩu và xác nhận mật khẩu không khớp.";
        return;
    }

    // Lấy dữ liệu từ form
    const data = {
        maND: "1412",
        tenTK: name.value,
        tenDN: username.value,
        matkhau: password.value,
        soDT: phone.value,
        diaChi: address.value
    };

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(APIurl, requestOptions)
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(errorData.message || "Đã xảy ra lỗi không xác định.");
                });
            }
            return response.text();
        })
        .then(data => {
            // console.log(data);
            errorMessageElement.textContent = ""; // Xóa thông báo lỗi nếu có
            if (data.match("falseND")) {
                errorMessageElement.textContent = "Tên tài khoản đã tồn tại."         
            } else if(data.match("falseSDT")){
                errorMessageElement.textContent = "Số điện thoại đã tồn tại."
            } else {
                showModal("Đăng ký thành công!");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

closeModal.onclick = function() {
    modal.style.display = "none";
}

modalOkButton.onclick = function() {
    window.location.href = '../frontEnd/pageLogin.html'; // Chuyển hướng đến trang đăng nhập
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function showModal(message) {
    modalMessage.textContent = message;
    modal.style.display = "flex";
}