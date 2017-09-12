DROP FUNCTION q01(cust_name text);
CREATE OR REPLACE FUNCTION q01(cust_name text) RETURNS void as $$
    declare
            vnam Text;
            trans_date DATE;
            trans_amt DECIMAL(20,2);
            
            c1 cursor for SELECT Vname, T_Date, Amount from vendor, transaction, customer WHERE
            vendor.Vno = transaction.Vno AND Cname = cust_name AND customer.Account = transaction.Account;
    begin
        raise notice '';
        open c1;
        loop
            fetch c1 into vnam, trans_date, trans_amt;
            exit when not found;
            raise notice 'Vendor Name: %', vnam;
            raise notice 'Vendor Date: %', trans_date;
            raise notice 'Vendor Amount: %', trans_amt;
            raise notice '';
        end loop;
        close c1;
    end;
$$ language plpgsql;
