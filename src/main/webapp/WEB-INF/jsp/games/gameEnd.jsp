<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	html,body,.container {
    height:100%;
	}
	.container-fluid {
		display:table;
		width: 100%;
		margin-top: -50px;
		padding: 50px 0 0 0; /*set left/right padding according to needs*/
		box-sizing: border-box;
		background-image: url('/resources/images/board/welcome3.png');
		background-repeat: no-repeat;
		background-size: 100% 100%;
	}

	.row {
		height: 100%;
		display: table-row;
	}

	.row .no-float {
		display: table-cell;
		float: none;
	}
	.board{
		background-image: url('/resources/images/board/board8.png');
  		background-repeat: no-repeat;
		background-size: 100% 100%;
		height: 80%;
	}
	.board-element{
		background-color: rgba(241, 202, 139, 0.811); 
		height: 10%;
	}
	.board-element-winner{
		background-color: rgba(255, 234, 2, 0.811); 
		height: 10%;
	}
	.board-border{
		border-style: solid;
		border-width: 5px;
		border-color: wheat;
		border-radius: 10px;
		margin: 2px
	}
	.board-border-white{
		border-style: solid;
		border-width: 5px;
		border-color: white;
		border-radius: 10px;
		margin: 2px
	}
	.current-player-border{
		border-style: solid;
		border-width: 5px;
		border-color: rgb(11, 44, 207);
		border-radius: 10px;
		margin: 2px
	}
	.message-input{
		width: 75%; 
		border-style: solid; 
		border-width: 1px; 
		border-color: wheat; 
		border-radius: 10px; 
		margin-left: 3px;
	}
	.profileImage{
		width: 50px;
		height: 50px;
	}

	.island{
		background-color: rgba(245, 222, 179, 0.6);
	}

</style>

<!DOCTYPE html>
<html>
	<sieteislas:htmlHeader/>
	<body class="container-fluid">
		<div style="text-align: center;" class="board-element">
			<h1>GAME FINISHED!</h1>
		</div>
		<div style="display:inline-flex; position:relative; left: 5%; width: 100%;">
			<c:forEach items="${playerPointsEndGame}" var="pointsMap">
				<c:choose>
					<c:when test="${pointsMap.player.equals(winner)}">
						<div style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); margin: 10px; text-align: center; width: 20%; height: 20%;" class="board-element-winner board-border">
							<div style="width: 100%; height: 100%;">
								<img src="${pointsMap.player.user.profileImage}" alt="image" style="width: 100%; height: 100%; object-fit: cover;">
							</div>
							<h4 style="color: black;"><c:out value="${pointsMap.player.user.username}" /></h4>
							<h4 style="color: black;">POINTS: <c:out value="${pointsMap.points}" /></h4>
							<p class="glyphicon glyphicon-king" style="color: rgb(112, 107, 107); font-size: 150%;">WINNER</p>
						</div>
					</c:when>
					<c:otherwise>
						<div style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); margin: 10px; text-align: center; width: 20%; height: 20%;" class="board-element board-border">
							<div style="width: 100%; height: 100%;">
								<img src="${pointsMap.player.user.profileImage}" alt="image" style="width: 100%; height: 100%; object-fit: cover;">
							</div>
							<h4 style="color: black;"><c:out value="${pointsMap.player.user.username}" /></h4>
							<h4 style="color: black;">POINTS: <c:out value="${pointsMap.points}" /></h4> 
							<br>         
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>    
		</div>
		<div style="text-align: center;">
			<a href="/" class="btn btn-default" style="height: 100px; width: 300px; font-size: 50;" >HOME</a>
		</div>



	</body>
	<sieteislas:footer/>
</html>