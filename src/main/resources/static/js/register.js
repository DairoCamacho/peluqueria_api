async function register() {
    let datos = {
        name: document.getElementById('name').value,
        lastName: document.getElementById('lastName').value,
        phone: document.getElementById('phone').value,
        birthday: document.getElementById('birthday').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        confirmPassword: document.getElementById('confirmPassword').value,
    };
    if (datos.password != datos.confirmPassword) {
        alert('Las contraseñas no coinciden');
        return;
    }

    const request = await fetch('api/client', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(datos),
    });
    const client = await request.json();
    console.log(client);
}