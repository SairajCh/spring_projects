package practice;

import java.util.HashSet;
import java.util.Set;

public class Category {

    private Category parentCategory;
    private Set<Category> childCategories;
    private String name;

    public Category() {
        childCategories = new HashSet<Category>();
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [childCategories=" + childCategories + ", name="
                + name + ", parentCategory="
                + ((parentCategory == null) ? null : parentCategory.getName())
                + "]";
        }




public static void main(String[] args) {
        Category books = new Category();
        books.setName("Books");
        books.setParentCategory(null);

        Category novels = new Category();
        novels.setName("Novels");
        novels.setParentCategory(books);

        books.getChildCategories().add(novels);
        //novels.setChildCategories(null);

        System.out.println("Books > " + books);
    }
}