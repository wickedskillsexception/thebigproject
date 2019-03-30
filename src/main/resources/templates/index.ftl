[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="en">
<head>

</head>
<body>

<div class="container">
	[#--<a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>--]
	</a>

	<ol class="breadcrumb">
		<li class="active"><a href="/">Home</a></li>

		[#if user??]
			<li><a href="/user">User</a></li>
		[#--<li><a href="/projects">Projects</a></li>--]
		[#--<li><a href="/logout">Logout</a>--]
			</li>
		[#else]
			<li><a href="/login">Login</a></li>
		[/#if]
	</ol>
</div>
</div>

</body>
</html>


