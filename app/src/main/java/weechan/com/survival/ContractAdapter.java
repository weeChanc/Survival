package weechan.com.survival;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/13 22:05
 */

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ContractViewHolder> {

    private List<String> contracts;

    public ContractAdapter(List<String> contracts) {
        assert contracts != null;
        this.contracts = contracts;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contract_item,null,false);
        ContractViewHolder holder = new ContractViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder, int i) {
        holder.contractName.setText(contracts.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return contracts.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder{

        TextView contractName;

        public ContractViewHolder(@NonNull View itemView) {
            super(itemView);
            this.contractName = itemView.findViewById(R.id.contract_name);
        }
    }
}
