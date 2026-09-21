<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Discount Calculator</title>
        <style>
            body { font-family: Arial, sans-serif; display: flex; justify-content: center; margin-top: 50px; background-color: #f8fafc; }
            .calculator-form { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 350px; }
            .form-group { margin-bottom: 15px; }
            label { display: block; margin-bottom: 5px; font-weight: bold; color: #1b2a7a; }
            input { width: 93%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
            button { background-color: #1b2a7a; color: white; padding: 10px; border: none; border-radius: 4px; width: 100%; cursor: pointer; font-weight: bold; }
        </style>
    </head>
    <body>
        <div class="calculator-form">
            <h2 style="text-align: center; color: #1b2a7a;">Discount Calculator</h2>
            <form action="display-discount" method="POST">
                <div class="form-group">
                    <label for="description">Product Description:</label>
                    <input type="text" id="description" name="description" required />
                </div>
                <div class="form-group">
                    <label for="price">List Price ($):</label>
                    <input type="number" id="price" name="price" step="0.01" min="0" required />
                </div>
                <div class="form-group">
                    <label for="discount">Discount Percent (%):</label>
                    <input type="number" id="discount" name="discount" step="0.1" min="0" max="100" required />
                </div>
                <button type="submit">Calculate Discount</button>
            </form>
        </div>
    </body>
</html>