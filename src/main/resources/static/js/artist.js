function getArtist(id) {
    fetch('http://localhost:8082/artists/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function (albumData) {
                    console.log(albumData)

                    // let row = document.getElementById("albumPlaceHolder");
                    // row.innerHTML = '';
                    // //let data = Object.keys(listData[0]);

                    // for (let album of albumData) {
                    //     //globalTaskList.push(list["items"]);
                    //     row.insertAdjacentHTML("beforeend", albumCard(album["name"]));
                    // }
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function albumCard(id, title) {
    return '<div class="tile">\
    <a href="/one_album.html?id='+id+'">\
    <img src="img/album/alterbridge.png">\
    <h3>'+title+'</h3>\
    <p>Artist/Creator</p>\
    </a>\
</div>'
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getArtist(id);