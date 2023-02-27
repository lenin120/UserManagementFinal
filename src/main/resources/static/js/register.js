// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function registerUsers() {

    let data = {};
    data.name = document.getElementById("exampleFirstName").value
    data.lastName = document.getElementById("exampleLastName").value
    data.email = document.getElementById("exampleInputEmail").value
    data.password = document.getElementById("exampleInputPassword").value

    let repeatPassword = document.getElementById("exampleRepeatPassword").value

    if(repeatPassword != data.password){
        alert("La contrasenia que escribiste es diferente")
        return
    }
    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    alert("Cuenta creada con exito");
    window.location.href = "login.html"
}

