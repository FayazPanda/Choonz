let cookie = document.cookie;

function userId() {
  if(cookie == "") {
    document.cookie = "userid=0; expires=Thu, 01 Jan 2070 00:00:00 UTC; path=/;";
    document.cookie = "name=0; expires=Thu, 01 Jan 2070 00:00:00 UTC; path=/;";
    document.cookie = "permission=0; expires=Thu, 01 Jan 2070 00:00:00 UTC; path=/;";
    return 0;
    
  }
  else {
    let userID = cookie
      .split('; ')
      .find(row => row.startsWith('userid'))
      .split('=')[1];
    return userID;
  }
}

function getPermission(){
  if(cookie == "") {
    document.cookie = "permission=0; expires=Thu, 01 Jan 2070 00:00:00 UTC; path=/;";
    return 0;
  }
  else{
    let permission = cookie
    .split('; ')
    .find(row => row.startsWith('permission'))
    .split('=')[1];
  return permission;
  }
 
}

function username() {
    let username = cookie
        .split('; ')
        .find(row => row.startsWith('name'))
        .split('=')[1];
    return username;
}

function loginCheck() {
    let userID = userId();

    let loggedIn = document.getElementById("logInVisibility");
    let loggedOut = document.getElementById("logInVisibilty2");
    let welcomeMesssage = document.getElementById("welcomeMessage");
    if (userID > 0) {
        loggedIn.style.visibility = "visible";
        loggedIn.style.display = "block";
        loggedOut.style.visibility = "hidden";
        loggedOut.style.display = "none";
        welcomeMesssage.innerHTML = "";
        welcomeMessage.append("Hi, " + username());
        return userID;
    } else {
        loggedOut.style.visibility = "visible";
        loggedOut.style.display = "block";
        loggedIn.style.visibility = "hidden";
        loggedIn.style.display = "none";
        return 0;
    }
}

loginCheck();

