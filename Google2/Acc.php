<?php
$con=mysqli_connect('localhost','root');

mysqli_select_db($con,'project');

$first =$_POST['first'];
$last =$_POST['last'];
$username =$_POST['username'];
$password =$_POST['password'];
$s="select*from table1 where username='$username'";
$result=mysqli_query($con,$s);
$n=mysqli_num_rows($result);
if($n==1)
{
  echo '<script>alert("Username already exist")</script>';
}
else
{
  $same="insert into table1(first,last,username,password) values ('$first','$last','$username','$password')";
  $show=mysqli_query($con,$same);
  echo "Done";
  header('location:google1.html');
}

?>