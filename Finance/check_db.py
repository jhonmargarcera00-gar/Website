from cs50 import SQL
db = SQL("sqlite:///finance.db")
tables = db.execute('SELECT name FROM sqlite_master WHERE type="table"')
print(tables)