<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consommateur</title>
<style><%@include file="/css/student.css"%>
 <%@include file="/css/bootstrap.css"%>
  <%@include file="/css/fas.css"%>
 
 </style>
</head>
<body>


<section class="intro">
  <div class="bg-image h-100" style="background-color: #6095F0;">
    <div class="mask d-flex align-items-center h-100">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-12">
            <div class="card shadow-2-strong" style="background-color: #f5f7fa;">
              <div class="card-body">
            
                <div class="table-responsive">
                
                  <form method="POST" action="conso" class="m-5">
                 
                 
                   
                
                   
                  <table class="table table-borderless mb-0">
                    <thead>
                      <tr>
                        
                        <th scope="col"> 
                        <div class="form-group">
                          <label>Objet :</label>
                            <input class="form-input" type="text"  id="objet" name="objet" />
                          </div>
                          </th>
                         <% if(request.getAttribute("message") != null){%>
                        <th scope="col">
                         <div class="form-group">
                          <label>Message  :</label>
                          <textarea class="form-input" rows="3" ><%= request.getAttribute("message") %></textarea>
                          </div>
                          </th>
                           </tr>
                           <%}%> 
                      
                     
                        
                        
                    </thead>
                    <tbody>
                     
                      
            
                      
                    </tbody>
                  </table>
                  <div class="m-5 "> <button type="submit" class="btn btn-sm btn-success px-5">Enregistrer</button></div>
                 
                   
                    
                   
                   
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


</body>
</html>