const APICart = "http://localhost:8080/api/GioHang";
document.addEventListener('DOMContentLoaded', function () {
    const maND = localStorage.getItem('maND');
    if (!maND) {
        console.error('maND not found in localStorage');
        return;
    }

    fetch(APICart + `?maND=${maND}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('API response:', data); 
        if (!Array.isArray(data)) {
            throw new TypeError('Expected an array from API');
        }
        const tableGioHang = document.getElementById('tableGioHang');
        data.forEach(sanPham => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${sanPham.tenSP}</td>
                <td>${sanPham.giaSP}</td>
                <td>${sanPham.soLuongSP}</td>
                <td><button class="xoaSP" data-masp="${sanPham.maSP}">Xóa Sản phẩm</button></td>
            `;
            tableGioHang.appendChild(row);
        });

        document.querySelectorAll('.xoaSP').forEach(button => {
            button.addEventListener('click', function () {
                const maSP = this.getAttribute('data-masp');
                xoaSanPham(maSP, this);
            });
        });
    })
    .catch(error => console.error('Error:', error));
});

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

            } else if (data.match("falseSDT")) {
     
            } else {
                console.log('xóa thành công:', data);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    console.log(maSP);
}

function xoaSanPham(maSP, button) {
    const sanPhamData = {
        maND: localStorage.getItem("maND"),
        maSP: maSP
    };
    fetch(APICart, {
        method: 'DELETE',
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
        console.log('Success:', data);
        const row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}