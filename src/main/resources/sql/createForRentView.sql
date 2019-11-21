CREATE VIEW ForRent AS
    (SELECT * FROM vehicle WHERE status = 'for_rent')