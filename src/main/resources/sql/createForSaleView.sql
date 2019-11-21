CREATE VIEW ForSale AS
    (SELECT * FROM vehicle WHERE status = 'for_sale')