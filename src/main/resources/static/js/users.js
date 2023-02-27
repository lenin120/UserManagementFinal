// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
  $('#users').DataTable();
    document.getElementById('userEmail').outerHTML = localStorage.email;
});

async function loadUsers() {
    const request = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
    });
    const users = await request.json();
    console.log(users)

    let htmlLIST = '';
    for(let user of users){
        let deleteButton = '<a href="#" onclick="deleteUser('+ user.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let phoneNumbertxt = user.phoneNumber == null ? '-': user.phoneNumber
        let userHTML = '<tr><td>'+user.id+'</td><td>'+user.name+' '+user.lastName+'</td><td>'+user.email+'</td><td>'+phoneNumbertxt+'</td><td>' +deleteButton + '</td></tr>'
        htmlLIST += userHTML
        console.log(htmlLIST)
    }
    document.querySelector('#users tbody').outerHTML = htmlLIST
}

function getHeaders() {
    return{
    'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token,
    }
}
async function deleteUser(id) {
    if(!confirm('Desea eliminar este usuario?')){
        return;
    }
    const request = await fetch('api/users/'+id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload()
}
