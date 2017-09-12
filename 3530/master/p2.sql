DROP FUNCTION q02(vend_name text);
CREATE OR REPLACE FUNCTION q02(vend_name text) RETURNS void as $$
    declare
            C_num Text;
            C_name Text;
            C_Province Text;
            
            c1 cursor for SELECT customer.Account, Cname, Province from vendor, transaction, customer WHERE
            vendor.Vno = transaction.Vno AND Vname = vend_name AND customer.Account = transaction.Account;
    begin
        raise notice '';
        open c1;
        loop
            fetch c1 into C_num, C_name, C_Province;
            exit when not found;
            raise notice 'Customer Number: %', C_num;
            raise notice 'Customer Name: %', C_name;
            raise notice 'Province: %', C_Province;
            raise notice '';
        end loop;
        close c1;
    end;
$$ language plpgsql;
