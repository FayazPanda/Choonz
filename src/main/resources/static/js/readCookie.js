let cookie = document.cookie;

function userId(){
   let userID=cookie
  .split('; ')
  .find(row => row.startsWith('userid'))
  .split('=')[1];
  return userID;
}
console.log(cookie);
