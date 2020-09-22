$(document).ready(function(){
// Animations init
new WOW().init();

var typed=new Typed(`#type`,{
     strings:[" Wildlife Tracker "," An application that allows Rangers to track wildlife sightings in the area. "],
     backSpeed:70,
     typeSpeed:80,
     smartBackspace:true,
     loop: true,
   })
})