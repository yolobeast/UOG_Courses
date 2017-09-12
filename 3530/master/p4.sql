DROP FUNCTION q04();
CREATE OR REPLACE FUNCTION q04() RETURNS VOID as $$
    declare
            C_num TEXT;
            C_Cname TEXT;
            T_Amount DECIMAL(20,2);
            V_Vname TEXT;
            T_Tdate DATE;
            
            c1 cursor for SELECT transaction.Account, Cname, transaction.Amount, vendor.Vname FROM vendor, transaction, customer WHERE
            vendor.Vno = transaction.Vno AND customer.Account = transaction.Account AND 
            T_Date = (SELECT max(T_Date) FROM transaction WHERE customer.Account = transaction.Account);
    begin
        
        raise notice '';
        open c1;
        loop
            fetch c1 into C_num, C_Cname, T_Amount, V_Vname;
            exit when not found;
            raise notice 'Account Number: %', C_num;
            raise notice 'Customer Name: %', C_Cname;
            raise notice 'Amount:  %', T_Amount;
            raise notice 'Vendor Name: %', V_Vname;
            raise notice '';
        end loop;
        close c1;
            
    end;
$$ language plpgsql;
