<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discount Result</title>
        <style>
            body { font-family: Arial, sans-serif; display: flex; justify-content: center; margin-top: 50px; background-color: #f8fafc; }
            .result-card { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 350px; }
            .row { margin-bottom: 12px; display: flex; justify-content: space-between; border-bottom: 1px dashed #eee; padding-bottom: 5px; }
            .label { font-weight: bold; color: #64748b; }
            .value { font-weight: bold; color: #1b2a7a; }
            .highlight { color: #f15a24; font-size: 18px; }
            .back-btn { display: block; text-align: center; margin-top: 20px; background-color: #1b2a7a; color: white; padding: 10px; text-decoration: none; border-radius: 4px; }
        </style>
    </head>
    <body>
        <div class="result-card">
            <h2 style="text-align: center; color: #1b2a7a;">Discount Calculation Result</h2>
            
            <div class="row">
                <span class="label">Product Description:</span>
                <span class="value">${requestScope.description}</span>
            </div>
            <div class="row">
                <span class="label">List Price:</span>
                <span class="value">$${requestScope.price}</span>
            </div>
            <div class="row">
                <span class="label">Discount Percent:</span>
                <span class="value">${requestScope.discountPercent}%</span>
            </div>
            <div class="row">
                <span class="label">Discount Amount:</span>
                <span class="value highlight">$${requestScope.discountAmount}</span>
            </div>
            <div class="row">
                <span class="label">Discount Price:</span>
                <span class="value highlight">$${requestScope.discountPrice}</span>
            </div>

            <a href="index.jsp" class="back-btn">Calculate Another Product</a>
        </div>
    </body>
</html>