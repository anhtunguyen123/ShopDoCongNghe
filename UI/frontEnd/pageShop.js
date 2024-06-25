document.getElementById('addProductButton').onclick = function() {
    document.getElementById('addProductModal').style.display = 'block';
};

document.getElementById('closeAddProductModal').onclick = function() {
    document.getElementById('addProductModal').style.display = 'none';
};

document.getElementById('confirmAddProductButton').onclick = function() {
    const productName = document.getElementById('productName').value;
    const productImage = document.getElementById('productImage').value;
    const productCategory = document.getElementById('productCategory').value;
    const productPrice = document.getElementById('productPrice').value;

    const data = {
        tenSP: productName,
        maShop: localStorage.getItem("maShop"),
        giaSP: productImage,
        loaiSP: productCategory,
        soLuongSP: productPrice
    };

    if (productName && productImage && productCategory && productPrice) {
        fetch("http://localhost:8080/api/SanPham", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log("test");
                console.log(data);
            })
            .catch(error => console.error('Error:', error));
    }
};



fetch("http://localhost:8080/api/SanPham/getAllSP", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(localStorage.getItem("maShop"))
})
    .then(response => response.json())
    .then(data => {
        const tableSP = document.getElementById('productTable');
        data.forEach((sanPham, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${sanPham.tenSP}</td>
                <td>${sanPham.giaSP}</td>
                <td>${sanPham.loaiSP}</td>
                <td>${sanPham.soLuongSP}</td>
                <td><button class="xoaSP">Xóa</button></td>`;
            tableSP.appendChild(row);
        });
        document.querySelectorAll('.xoaSP').forEach((button, index) => {
            button.addEventListener('click', function() {
                xoaSanPham(data[index].maSP);
            });
        });
    })
    .catch(error => console.error('Error:', error));

function xoaSanPham(maSP){
    const sanPhamData = {
        maSP: maSP,
        soLuongSP: "delete"
    };
    fetch("http://localhost:8080/api/SanPham", {
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
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    console.log(maSP);
}