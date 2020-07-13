package io.shivamvk.bareilly

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.hendraanggrian.socialview.commons.Hashtag
import com.hendraanggrian.socialview.commons.HashtagAdapter
import com.hendraanggrian.socialview.commons.Mention
import com.hendraanggrian.socialview.commons.MentionAdapter
import com.hendraanggrian.widget.SocialAutoCompleteTextView
import io.shivamvk.bareilly.adapters.TrendingTagsAdapter
import io.shivamvk.bareilly.databinding.ActivityCreatePostBinding
import io.shivamvk.bareilly.utils.CustomeProgressDialog

class CreatePostActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityCreatePostBinding
    private val IMAGE_PICKER_CODE = 762

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            init()
        } catch (e :Exception){
            e.printStackTrace()
        }
    }

    private fun setUpTrendingTags() {
        binding.trendingTagsListView.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
        var trendingTags = ArrayList<String>()
        trendingTags.add("corona")
        trendingTags.add("jhumka")
        trendingTags.add("corona")
        trendingTags.add("jhumka")
        trendingTags.add("corona")
        trendingTags.add("jhumka")
        trendingTags.add("corona")
        trendingTags.add("jhumka")
        var adapter = TrendingTagsAdapter(baseContext, trendingTags, binding.postContentView);
        binding.trendingTagsListView.adapter = adapter
    }

    private fun setSelectedImage(data: Intent?) {
        binding.imageOptions.visibility = View.GONE
        binding.selectedImageView.visibility = View.VISIBLE
        binding.closeSelectedImage.visibility = View.VISIBLE
        binding.selectedImageView.setImageURI(data?.data)
    }

    private fun removeSelectedImage() {
        binding.imageOptions.visibility = View.VISIBLE
        binding.selectedImageView.visibility = View.GONE
        binding.closeSelectedImage.visibility = View.GONE
    }

    private fun init(){
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.BottomSheetActivityDarkTheme);
        } else {
            setTheme(R.style.BottomSheetActivityLightTheme);
        }
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpTrendingTags()
        binding.closeActivity.setOnClickListener(this)
        binding.btnPost.setOnClickListener(this)
        binding.imageOptionCamera.setOnClickListener(this)
        binding.imageOptionGallery.setOnClickListener(this)
        binding.closeSelectedImage.setOnClickListener(this)
        var textView = SocialAutoCompleteTextView<Hashtag, Mention>(baseContext)
        textView = findViewById(R.id.post_content_view)
        textView.setHashtagAdapter(HashtagAdapter(baseContext)) // or use custom adapter
        textView.setMentionAdapter(MentionAdapter(baseContext))// or use custom adapter

        textView.getHashtagAdapter().add(Hashtag("follow"))
        textView.getHashtagAdapter().add(Hashtag("followme"))
        textView.getHashtagAdapter().add(Hashtag("followmeorillkillyou"))
        textView.getMentionAdapter().add(Mention("dirtyhobo"))
        textView.getMentionAdapter().add(Mention("Regular Hobo"))
        textView.getMentionAdapter().add(Mention("hendraanggrian"))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.close_activity -> {
                finish()
            }
            R.id.btn_post -> {
                var progressDialog = CustomeProgressDialog(this)
                progressDialog.show(resources.getString(R.string.creating_post))
            }
            R.id.image_option_camera -> {
                ImagePicker.with(this)
                    .cameraOnly()
                    .crop(4f, 4f)
                    .compress(3072)
                    .start(IMAGE_PICKER_CODE)
            }
            R.id.image_option_gallery -> {
                ImagePicker.with(this)
                    .galleryOnly()
                    .crop(4f, 4f)
                    .compress(3072)
                    .start(IMAGE_PICKER_CODE)
            }
            R.id.close_selected_image -> {
                removeSelectedImage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode) {
                IMAGE_PICKER_CODE -> {
                    setSelectedImage(data)
                }
            }
        }
    }
}