const APIurl = "http://localhost:8080/api/SanPham";
const APICart = "http://localhost:8080/api/GioHang";

const addShopButton = document.getElementById('addShopButton');
const addShopModal = document.getElementById('addShopModal');
const closeAddShopModal = document.getElementById('closeAddShopModal');
const confirmAddShopButton = document.getElementById('confirmAddShopButton');
const login_div = document.getElementById("login");

// Phần tạo shop thành công hoặc lỗi
const successModal = document.getElementById('successModal');
const successModalOkButton = document.getElementById('successModalOkButton');
const successMessage = document.getElementById('successMessage');
const errorMessage = document.getElementById('errorMessage');

fetch("http://localhost:8080/api/Shop/check", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(localStorage.getItem("maND"))
})
    .then(response => response.text())
    .then(data => {
        if (!data.match("0")) {
            localStorage.setItem("maShop", data);
            addShopButton.innerHTML = "Shop";
            addShopButton.addEventListener('click', function () {
                window.location.href = "pageShop.html";
            });
        } else {
            addShopButton.addEventListener('click', function () {
                addShopModal.style.display = 'flex';
            });
        }
    })
    .catch(error => console.error('Error:', error));

document.addEventListener('DOMContentLoaded', function () {
    displayUsername();
    fetch(APIurl)
        .then(response => response.json())
        .then(data => {
            const tableSP = document.getElementById('tableSanPham').getElementsByTagName('tbody')[0];

            data.forEach((sanPham, index) => {
                const titleRow = document.createElement('tr');
                const titleCell = document.createElement('td');
                titleCell.setAttribute('colspan', '4');
                titleCell.textContent = `Sản phẩm: ${sanPham.tenSP}`;
                titleRow.appendChild(titleCell);
                tableSP.appendChild(titleRow);

                const row = document.createElement('tr');

                const giaSPCell = document.createElement('td');
                giaSPCell.textContent = `Giá: ${sanPham.giaSP}`;

                const soLuongSPCell = document.createElement('td');
                soLuongSPCell.textContent = `Số lượng còn: ${sanPham.soLuongSP}`;

                const thaoTacCell = document.createElement('td');
                thaoTacCell.style.textAlign = 'center';
                const addButton = document.createElement('button');
                addButton.textContent = 'Thêm giỏ hàng';
                addButton.className = 'addSP';
                addButton.addEventListener('click', function () {
                    addSanPham(sanPham.maSP);
                });
                thaoTacCell.appendChild(addButton);
                row.appendChild(giaSPCell);
                row.appendChild(soLuongSPCell);
                row.appendChild(thaoTacCell);

                tableSP.appendChild(row);
            });
        })
        .catch(error => console.error('Error:', error));
});


function displayUsername() {
    const loginInfoDiv = document.getElementById('login-info');
    const loginButton = document.getElementById('loginButton');
    const username = localStorage.getItem('tenTK');
    const maND = localStorage.getItem("maND");
    console.log('Local storage tenTK:', username); // Kiểm tra giá trị của tenTK trong localStorage
    console.log('Local storage maND:', maND);
    if (username) {
        loginInfoDiv.style.display = 'block';
        loginButton.style.display = 'none';
        document.getElementById('welcome-message').textContent = `Xin chào, ${username}`;
        document.getElementById('logoutButton').addEventListener('click', function () {
            localStorage.removeItem('tenTK');
            localStorage.removeItem('maND');
            location.reload();
        });
    }
}


function addSanPham(maSP) {
    const sanPhamData = {
        maND: localStorage.getItem("maND"),
        maSP: maSP
    };
    fetch(APICart, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(sanPhamData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            if (data.match("falseTenShop")) {
                // Do something
            } else if (data.match("falseSDT")) {
                // Do something
            } else {
                console.log('Success:', data); // Sửa thông báo
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    console.log(maSP);
}


closeAddShopModal.addEventListener('click', function () {
    addShopModal.style.display = 'none';
});

window.addEventListener('click', function (event) {
    if (event.target === addShopModal) {
        addShopModal.style.display = 'none';
    }
});

confirmAddShopButton.addEventListener('click', function () {
    const shopName = document.getElementById('shopName').value;
    const shopPhone = document.getElementById('shopPhone').value;
    const shopAddress = document.getElementById('shopAddress').value;

    // Gửi thông tin shop mới đến server
    const shopData = {
        tenShop: shopName,
        soDTShop: shopPhone,
        diaChiShop: shopAddress,
        maND: localStorage.getItem("maND")
    };

    fetch('http://localhost:8080/api/Shop', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(shopData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            if (data.match("falseTenShop")) {
                console.log("falseTenShop");
            } else if (data.match("falseSDT")) {
                console.log("falseSDT");
            } else if (data.match("1")) {
                showModal("success"); // Sửa thông báo
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

function showModal(message) {
    successMessage.textContent = message;
    successModal.style.display = 'flex';
}

successModalOkButton.addEventListener('click', function () {
    successModal.style.display = 'none';
    window.location.href = "pageShop.html";
});
