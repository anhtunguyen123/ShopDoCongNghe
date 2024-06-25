
const APIurl = "http://localhost:8080/api/nguoiDung"

const btnLogin = document.getElementById('loginForm');
const username = document.getElementById('username');
const password = document.getElementById('password');
const errorMessage = document.getElementById('errorMessage');
const tenTK = document.getElementById('login');

btnLogin.addEventListener('submit', function(event) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của form

    // Xóa thông báo lỗi trước đó
    errorMessage.textContent = '';

    // Lấy dữ liệu từ form
    const data = {
        tenDN: username.value,
        matkhau: password.value
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
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(user => {
        console.log('Login API response:', user); // Kiểm tra phản hồi từ API đăng nhập
            if(user){
                localStorage.clear();
                localStorage.setItem("maND", user.maND); // Lưu mã người dùng vào localStorage
                localStorage.setItem("tenTK", user.tenTK);
                window.location.href = 'pageProduct.html';
            } else{
            errorMessage.textContent = 'Sai tài khoản hoặc mật khẩu';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        errorMessage.textContent = 'Có lỗi xảy ra. Vui lòng thử lại sau.';
    })
})


