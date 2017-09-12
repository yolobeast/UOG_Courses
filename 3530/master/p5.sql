DROP FUNCTION q05();
CREATE OR REPLACE FUNCTION q05() RETURNS void as $$
    declare
            V_Vno TEXT;
            V_Vname TEXT;
            V_Vbalance DECIMAL(20,2);
            T_Amount DECIMAL(20,2);
            
            VV_Vno TEXT;
            
            c1 cursor for SELECT transaction.Vno, sum(Amount) FROM vendor, transaction WHERE
            vendor.Vno = transaction.Vno GROUP BY transaction.Vno;
            
            c2 cursor for SELECT DISTINCT transaction.Vno, Vname, Vbalance FROM transaction, vendor, customer WHERE 
            vendor.Vno = transaction.Vno AND customer.Account = transaction.Account;
    begin
        open c1;
        loop
            fetch c1 into VV_Vno, T_Amount;
            exit when not found;
            update vendor set Vbalance = Vbalance + T_Amount WHERE Vno = VV_Vno;
        end loop;
        close c1;
        
        raise notice '';
        
        open c2;
        loop
            fetch c2 into V_Vno, V_Vname, V_Vbalance;
            exit when not found;
            raise notice 'Vendor Number: %', V_Vno;
            raise notice 'Vendor Name: %', V_Vname;
            raise notice 'New Balance: %', V_Vbalance;
            raise notice '';

        end loop;
        close c2;
    end;
$$ language plpgsql;
