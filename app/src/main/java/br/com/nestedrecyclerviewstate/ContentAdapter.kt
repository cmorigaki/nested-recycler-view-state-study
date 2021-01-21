package br.com.nestedrecyclerviewstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.nestedrecyclerviewstate.databinding.BannerBinding
import br.com.nestedrecyclerviewstate.databinding.CarouselBinding

private enum class ViewType {
    BANNER,
    CAROUSEL,
}

class ContentAdapter : ListAdapter<Content, ContentAdapter.ViewHolder>(ContentAdapterDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.BANNER.ordinal ->
                ViewHolder.BannerViewHolder(
                    binding = BannerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            ViewType.CAROUSEL.ordinal ->
                ViewHolder.CarouselViewHolder(
                    binding = CarouselBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                )
            else -> throw ClassNotFoundException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Content.Banner -> ViewType.BANNER.ordinal
            is Content.Carousel -> ViewType.CAROUSEL.ordinal
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.BANNER.ordinal -> (holder as ViewHolder.BannerViewHolder).bind(
                getItem(position) as Content.Banner
            )
            ViewType.CAROUSEL.ordinal -> (holder as ViewHolder.CarouselViewHolder).bind(
                getItem(position) as Content.Carousel
            )
            else -> throw ClassNotFoundException()
        }
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class BannerViewHolder(
            private val binding: BannerBinding
        ) : ViewHolder(binding.root) {

            fun bind(content: Content.Banner) {
                with(binding) {
                    txtBanner.text = root.context.getString(R.string.banner_text, content.id)
                }
            }
        }

        class CarouselViewHolder(
            private val binding: CarouselBinding,
        ) : ViewHolder(binding.root) {

            private lateinit var content: Content.Carousel

            init {
                binding.carousel.apply {
                    adapter = CarouselAdapter()
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }
            }

            fun bind(content: Content.Carousel) {
                this.content = content
                with(binding) {
                    (carousel.adapter as CarouselAdapter).submitList(content.list)
                }
            }
        }
    }

    private class ContentAdapterDiffUtil : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) =
            oldItem::class.simpleName == newItem::class.simpleName

        override fun areContentsTheSame(oldItem: Content, newItem: Content) =
            oldItem.id == newItem.id

    }
}
