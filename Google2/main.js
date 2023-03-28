const searchInput = document.querySelector("#search-input");

searchInput.addEventListener("keydown",function(event)
{
    if(event.code === "Enter"){
        search();
    }
});

function search(){
    const input = searchInput.value;
    window.location.href="https://www.google.com/search?q=" + input + "&rlz=1C1CHBF_enIN923IN923&oq=" + input + "&aqs=chrome..69i57j46i433j0i433j46i433l2j0j46i433j0i433l2j0.2792j0j7&sourceid=chrome&ie=UTF-8"
}
